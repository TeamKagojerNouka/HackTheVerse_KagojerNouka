from django.db import models

# Create your models here.


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


class Delivery(models.Model):

    datetime = models.DateField('scheduled datetime', null=True, blank=True)
    address = models.CharField('address', max_length=512, null=True, blank=True)
    stage = models.CharField('stage of delivery', max_length=64, null=True, blank=True)

    delivery_man = models.ForeignKey(DeliveryMan, related_name='deliveries', on_delete=models.SET_NULL, null=True, blank=True)
    business = models.ForeignKey(Business, related_name='deliveries', on_delete=models.SET_NULL, null=True, blank=True)
    customer = models.ForeignKey(Customer, related_name='deliveries', on_delete=models.SET_NULL, null=True, blank=True)
