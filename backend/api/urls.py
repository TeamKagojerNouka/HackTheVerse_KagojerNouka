from django.conf.urls import url
from django.urls import include
from rest_framework.routers import DefaultRouter

from . import views

router = DefaultRouter()
router.register('business', views.BusinessViewset)
router.register('customer', views.CustomerViewset)
router.register('deliveryman', views.DeliveryManViewset)
router.register('service', views.ServiceViewset)
router.register('delivery', views.DeliveryViewset)


urlpatterns = [
    url('', include(router.urls)),

    url('test/', views.test_endpoint, name='testing')
]
