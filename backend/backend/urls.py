from django.contrib import admin
from django.urls import path, include
from rest_framework import response, status, decorators


@decorators.api_view(['GET'])
def test_endpoint(request):
    return response.Response({'details': 'working'}, status=status.HTTP_200_OK)


urlpatterns = [
    path('admin/', admin.site.urls),
    path('auth/', include('rest_framework_social_oauth2.urls')),
    path('test/', test_endpoint),
]
