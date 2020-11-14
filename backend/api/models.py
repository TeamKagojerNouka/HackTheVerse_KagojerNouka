import os
import json
from uuid import uuid4
from django.db import models
from django.conf import settings
import qrcode


class Location(models.Model):

    location_name = models.CharField('location name', max_length=128)
    region = models.CharField('location under region', max_length=128)


class DeliveryMan(models.Model):

    full_name = models.CharField('delivery man name', max_length=128)
    nid = models.CharField('national id', max_length=128, unique=True)
    date_of_birth = models.DateField('date of birth', null=True, blank=True)
    phone_num = models.CharField('phone number', max_length=16, unique=True)
    reliability_index = models.DecimalField('reliability of delivery', decimal_places=2, max_digits=10, default=0)

    def __str__(self):
        return f'{self.id}:{self.full_name}'


class Service(models.Model):

    service_name = models.CharField('delivery service name', max_length=128, unique=True)
    active_since = models.DateField('start of operation', null=True, blank=True)
    category = models.CharField('category of products delivered', max_length=64, null=True, blank=True)

    locations = models.ManyToManyField(Location)

    def __str__(self):
        return f'{self.id}:{self.service_name}'


class Business(models.Model):

    business_name = models.CharField('business name', max_length=128, unique=True)
    category = models.CharField('category of business', max_length=64, null=True, blank=True)
    address = models.CharField('address', max_length=512, null=True, blank=True)
    active_since = models.DateField('start of operation', null=True, blank=True)

    service = models.ForeignKey(Service, related_name='clients', on_delete=models.SET_NULL, null=True, blank=True)

    def __str__(self):
        return f'{self.id}:{self.business_name}'


class Customer(models.Model):

    full_name = models.CharField('full name', max_length=128, unique=True)
    phone_num = models.CharField('phone number', max_length=16, unique=True)
    address = models.CharField('address', max_length=512, null=True, blank=True)

    def __str__(self):
        return f'{self.id}:{self.full_name}'


class Delivery(models.Model):

    datetime = models.DateTimeField('scheduled datetime', null=True, blank=True)
    address = models.CharField('address', max_length=512, null=True, blank=True)
    stage = models.CharField('stage of delivery', max_length=64, default='ready-for-pickup', blank=True)
    satisfaction_level = models.SmallIntegerField('satisfact level (out of 10)', null=True, blank=True)
    success = models.BooleanField('delivery successful', default=False, blank=True)
    pickup_time = models.IntegerField('time to pickup (minutes)', null=True, blank=False)

    delivery_man = models.ForeignKey(DeliveryMan, related_name='deliveries', on_delete=models.SET_NULL, null=True, blank=True)
    business = models.ForeignKey(Business, related_name='deliveries', on_delete=models.SET_NULL, null=True, blank=True)
    service = models.ForeignKey(Service, related_name='deliveries', on_delete=models.SET_NULL, null=True, blank=True)
    customer = models.ForeignKey(Customer, related_name='deliveries', on_delete=models.SET_NULL, null=True, blank=True)

    qr_code_text = models.CharField(max_length=40, default=uuid4(), editable=False)
    qr_code_img = models.ImageField(null=True, blank=True)

    def get_path_in_filesystem(self):
        return os.path.join(settings.MEDIA_ROOT, f'{self.qr_code_text}.png')

    def get_path_to_save_in_image_field(self):
        return f'{self.qr_code_text}.png'

    def get_data_to_store_in_qr_code(self):
        return json.dumps({'id': self.id, 'uuid': str(self.qr_code_text)})

    def save_image_in_filesystem(self):
        qr_code = qrcode.QRCode(
            version=1,
            error_correction=qrcode.constants.ERROR_CORRECT_L,
            box_size=10,
            border=4
        )
        qr_code.add_data(self.get_data_to_store_in_qr_code())
        if not os.path.exists(settings.MEDIA_ROOT):
            os.mkdir(settings.MEDIA_ROOT)
        qr_code.make_image().save(self.get_path_in_filesystem())

    def save(self, *args, **kwargs):
        super(Delivery, self).save(*args, **kwargs)
        if not self.qr_code_img:
            self.save_image_in_filesystem()
            self.qr_code_img = self.get_path_to_save_in_image_field()

    def get_image_url(self):
        return os.path.join(settings.MEDIA_URL, str(self.qr_code_img))

    def __str__(self):
        return f'{self.id}:{self.datetime}'
