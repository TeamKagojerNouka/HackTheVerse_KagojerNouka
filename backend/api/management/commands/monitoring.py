from django.core.management.base import BaseCommand
from django.db import transaction
from django.db.models import Avg

from api.models import *


class Command(BaseCommand):
    help = 'Monitors bad actors'

    @transaction.atomic
    def handle(self, *args, **options):

        delivery_man = Delivery.objects.values('delivery_man_id').annotate(
            average_pickup_time=Avg('pickup_time'),
            average_satisfaction_score=Avg('satisfaction_level')
        )

        # todo: Calculate reliability_index (normalized out of 10) with the following features
        # average_satisfaction_level, log(successful_delivery_count), rate_of_failure, average_pickup_time

        # todo: Flag users under a certain threshold of reliability_index
