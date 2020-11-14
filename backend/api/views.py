import datetime

from django.db.models import Avg
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

    @action(detail=True, methods=['POST'])
    def pickup(self, request, pk):

        try:
            if self.is_valid_request():
                delivery = self.get_object()
                delivery.status = 'picked-up'
                delivery.delivery_man_id = request.data.get('delivery_man_id')
                delivery.pickup_time = int((datetime.datetime.now() - delivery.datetime).seconds / 60)
                delivery.save()
                return Response({'success': True, 'message': 'Pickup Successful!'})
            else:
                return Response({'success': False, 'message': 'Pickup Failed!'})
        except Exception as e:
            return Response({'success': False, 'message': str(e)})

    @action(detail=True, methods=['POST'])
    def complete(self, request, pk):
        try:
            if self.is_valid_request():
                delivery = self.get_object()
                delivery.status = 'complete'
                delivery.save()
                return Response({'success': True, 'message': 'Delivery Successful!'})
            else:
                return Response({'success': False, 'message': 'Delivery Failed!'})
        except Exception as e:
            return Response({'success': False, 'message': str(e)})


@api_view(http_method_names=['GET'])
def suggest_services(request):

    location = request.GET.get('location')
    category = request.GET.get('category')
    time = float(request.GET.get('time', 1000))

    services = Service.objects\
        .filter(locations__location_name=location, category=category)\
        .annotate(time=Avg('deliveries__pickup_time')).filter(time__lte=time).all()

    serializer = ServiceSerializer(services, many=True, fields=['id', 'service_name'])

    return Response(serializer.data)


@api_view(http_method_names=['GET'])
def suggest_delivery_men(request):

    data = request.GET.get('data').split(',')

    if data is None:
        Response({'success': False, 'message': 'Incorrect Response'})

    delivery_men = DeliveryMan.objects.filter(id__in=data).order_by('-reliability_index').all()
    serializer = DeliveryManSerializer(delivery_men, many=True, fields=['id', 'full_name', 'reliability_index'])

    return Response(serializer.data)
