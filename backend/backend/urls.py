from django.contrib import admin
from django.urls import path, include
from rest_framework import response, status, decorators


urlpatterns = [
    path('admin/', admin.site.urls),
    path('auth/', include('rest_framework_social_oauth2.urls')),

    path('api/', include('api.urls')),
]
