import random

from rest_framework import viewsets
from rest_framework.decorators import api_view, action
from rest_framework.response import Response

from api.models import *
from api.serializers import *


class BusinessViewset(viewsets.ModelViewSet):

    queryset = Business.objects.all()
    serializer_class = BusinessSerializer


class CustomerViewset(viewsets.ModelViewSet):

    queryset = Customer.objects.all()
    serializer_class = CustomerSerializer


class DeliveryManViewset(viewsets.ModelViewSet):

    queryset = DeliveryMan.objects.all()
    serializer_class = DeliveryManSerializer


class ServiceViewset(viewsets.ModelViewSet):

    queryset = Service.objects.all()
    serializer_class = ServiceSerializer


class DeliveryViewset(viewsets.ModelViewSet):

    queryset = Delivery.objects.all()
    serializer_class = DeliverySerializer

    @action(detail=True, methods=['GET'])
    def pickup(self, request, pk):

        delivery = self.get_object()

        # todo: check QR Code, set delivery man, change status to 'picked-up'
        if random.choice([True, False]):
            delivery.status = 'picked-up'
            delivery.save()
            return Response('You have picked up the goods, young padawan!')
        else:
            return Response('You have FAILED, young padawan!')

    @action(detail=True, methods=['GET'])
    def complete(self, request, pk):

        delivery = self.get_object()

        # todo: check QR Code, change status to 'complete'
        if random.choice([True, False]):
            delivery.status = 'complete'
            delivery.save()
            return Response('You have completed the delivery, young padawan!')
        else:
            return Response('You have FAILED, young padawan!')


@api_view(['GET'])
def test_endpoint(request):
    return Response('Welcome, young padawan!')
