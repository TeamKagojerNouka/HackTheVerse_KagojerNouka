from django.conf.urls import url
from django.urls import include
from drf_yasg import openapi
from drf_yasg.views import get_schema_view
from rest_framework.routers import DefaultRouter

from . import views

router = DefaultRouter()
router.register('business', views.BusinessViewSet)
router.register('customer', views.CustomerViewSet)
router.register('deliveryman', views.DeliveryManViewSet)
router.register('service', views.ServiceViewSet)
router.register('delivery', views.DeliveryViewSet)


urlpatterns = [
    url('suggest/deliveryman/', views.suggest_delivery_men, name='suggest-delivery-men'),
    url('suggest/service/', views.suggest_services, name='suggest-service'),

    url('', include(router.urls)),
]

# Schema

schema_view = get_schema_view(
    openapi.Info(
        title="Kagojer Nouka API",
        default_version='v1',
        description="Kagojer Nouka API",
        terms_of_service="https://www.google.com/policies/terms/",
        contact=openapi.Contact(email="tahmeedtarek@gmail.com"),
    ),
    public=True
)

urlpatterns += [
    url(r'^swagger/$', schema_view.with_ui('swagger', cache_timeout=0), name='schema-swagger-ui'),
]
