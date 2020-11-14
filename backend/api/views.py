from rest_framework.decorators import action, api_view
from rest_framework.response import Response


# Create your views here.

@api_view(['GET'])
def test_endpoint(request):
    return Response({'details': 'working'})
