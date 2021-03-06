# Generated by Django 3.1.3 on 2020-11-14 18:58

from django.db import migrations, models
import uuid


class Migration(migrations.Migration):

    dependencies = [
        ('api', '0010_auto_20201114_1844'),
    ]

    operations = [
        migrations.AlterField(
            model_name='delivery',
            name='qr_code_text',
            field=models.CharField(default=uuid.UUID('da3ffb91-ae20-4940-9c6a-4ea4a122ef14'), editable=False, max_length=40),
        ),
        migrations.AlterField(
            model_name='delivery',
            name='satisfaction_level',
            field=models.SmallIntegerField(blank=True, null=True, verbose_name='satisfaction level (out of 10)'),
        ),
    ]
