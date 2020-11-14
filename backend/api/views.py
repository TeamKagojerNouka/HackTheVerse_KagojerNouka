from rest_framework import viewsets
from rest_framework.decorators import api_view, action
from rest_framework.response import Response
from api.serializers import *


class BusinessViewSet(viewsets.ModelViewSet):
    queryset = Business.objects.all()
    serializer_class = BusinessSerializer


class CustomerViewSet(viewsets.ModelViewSet):
    queryset = Customer.objects.all()
    serializer_class = CustomerSerializer


class DeliveryManViewSet(viewsets.ModelViewSet):
    queryset = DeliveryMan.objects.all()
    serializer_class = DeliveryManSerializer


class ServiceViewSet(viewsets.ModelViewSet):
    queryset = Service.objects.all()
    serializer_class = ServiceSerializer


class DeliveryViewSet(viewsets.ModelViewSet):
    queryset = Delivery.objects.all()
    serializer_class = DeliverySerializer

    def is_valid_request(self):
        delivery = self.get_object()
        return delivery.qr_code_text == self.request.data.get('uuid')

    @action(detail=True, methods=['GET'])
    def pickup(self, request, pk):
        if self.is_valid_request():  # todo: check whether the product is in proper state before
            delivery = self.get_object()
            delivery.status = 'picked-up'
            delivery.save()
            return Response('You have picked up the goods, young padawan!')
        else:
            return Response('You have FAILED, young padawan!')

    @action(detail=True, methods=['GET'])
    def complete(self, request, pk):
        if self.is_valid_request():
            delivery = self.get_object()
            delivery.status = 'complete'
            delivery.save()
            return Response('You have completed the delivery, young padawan!')
        else:
            return Response('You have FAILED, young padawan!')
