from django.conf.urls import url
from django.urls import include
from rest_framework.routers import DefaultRouter

from . import views

router = DefaultRouter()
router.register('business', views.BusinessViewSet)
router.register('customer', views.CustomerViewSet)
router.register('deliveryman', views.DeliveryManViewSet)
router.register('service', views.ServiceViewSet)
router.register('delivery', views.DeliveryViewSet)


urlpatterns = [
    url('', include(router.urls)),

    url('test/', views.test_endpoint, name='testing')
]
