from rest_framework import serializers
from .models import *


class DynamicFieldsModelSerializer(serializers.ModelSerializer):

    def __init__(self, *args, **kwargs):
        fields = kwargs.pop('fields', None)
        super(DynamicFieldsModelSerializer, self).__init__(*args, **kwargs)

        if fields is not None:
            allowed = set(fields)
            existing = set(self.fields)
            for field_name in existing - allowed:
                self.fields.pop(field_name)


class DeliveryManSerializer(DynamicFieldsModelSerializer):

    class Meta:
        model = DeliveryMan
        fields = ('id', 'full_name', 'nid', 'date_of_birth', 'phone_num', 'reliability_index', 'is_blacklisted')


class ServiceSerializer(DynamicFieldsModelSerializer):

    class Meta:
        model = Service
        fields = ('id', 'service_name', 'active_since', 'category', 'locations', 'is_blacklisted')


class BusinessSerializer(DynamicFieldsModelSerializer):

    class Meta:
        model = Business
        fields = ('id', 'business_name', 'category', 'address', 'active_since')


class CustomerSerializer(DynamicFieldsModelSerializer):

    class Meta:
        model = Customer
        fields = ('id', 'full_name', 'phone_num', 'address')


class DeliverySerializer(DynamicFieldsModelSerializer):
    qr_code = serializers.SerializerMethodField()

    class Meta:
        model = Delivery
        fields = ('id', 'datetime', 'address', 'stage', 'delivery_man', 'business', 'customer',
                  'qr_code')

    def get_qr_code(self, obj):
        return obj.get_image_url()


class LocationSerializer(DynamicFieldsModelSerializer):

    class Meta:
        model = Location
        fields = ('id', 'location_name', 'region')

