# Generated by Django 3.1.3 on 2020-11-14 10:14

from django.db import migrations, models
import django.db.models.deletion


class Migration(migrations.Migration):

    initial = True

    dependencies = [
    ]

    operations = [
        migrations.CreateModel(
            name='Business',
            fields=[
                ('id', models.AutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('business_name', models.CharField(max_length=128, unique=True, verbose_name='business name')),
                ('category', models.CharField(blank=True, max_length=64, null=True, verbose_name='category of business')),
                ('address', models.CharField(blank=True, max_length=512, null=True, verbose_name='address')),
                ('active_since', models.DateField(blank=True, null=True, verbose_name='start of operation')),
            ],
        ),
        migrations.CreateModel(
            name='Customer',
            fields=[
                ('id', models.AutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('full_name', models.CharField(max_length=128, unique=True, verbose_name='full name')),
                ('phone_num', models.CharField(max_length=16, unique=True, verbose_name='phone number')),
                ('address', models.CharField(blank=True, max_length=512, null=True, verbose_name='address')),
            ],
        ),
        migrations.CreateModel(
            name='DeliveryMan',
            fields=[
                ('id', models.AutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('full_name', models.CharField(max_length=128, verbose_name='delivery man name')),
                ('nid', models.CharField(max_length=128, unique=True, verbose_name='national id')),
                ('date_of_birth', models.DateField(blank=True, null=True, verbose_name='date of birth')),
                ('phone_num', models.CharField(max_length=16, unique=True, verbose_name='phone number')),
                ('reliability_index', models.DecimalField(decimal_places=2, default=0, max_digits=10, verbose_name='reliability of delivery')),
            ],
        ),
        migrations.CreateModel(
            name='Service',
            fields=[
                ('id', models.AutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('business_name', models.CharField(max_length=128, unique=True, verbose_name='delivery service name')),
                ('active_since', models.DateField(blank=True, null=True, verbose_name='start of operation')),
            ],
        ),
        migrations.CreateModel(
            name='Delivery',
            fields=[
                ('id', models.AutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('datetime', models.DateField(blank=True, null=True, verbose_name='scheduled datetime')),
                ('address', models.CharField(blank=True, max_length=512, null=True, verbose_name='address')),
                ('stage', models.CharField(blank=True, max_length=64, null=True, verbose_name='stage of delivery')),
                ('business', models.ForeignKey(blank=True, null=True, on_delete=django.db.models.deletion.SET_NULL, related_name='deliveries', to='api.business')),
                ('customer', models.ForeignKey(blank=True, null=True, on_delete=django.db.models.deletion.SET_NULL, related_name='deliveries', to='api.customer')),
                ('delivery_man', models.ForeignKey(blank=True, null=True, on_delete=django.db.models.deletion.SET_NULL, related_name='deliveries', to='api.deliveryman')),
            ],
        ),
        migrations.AddField(
            model_name='business',
            name='service',
            field=models.ForeignKey(blank=True, null=True, on_delete=django.db.models.deletion.SET_NULL, related_name='clients', to='api.service'),
        ),
    ]
