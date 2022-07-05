import { Component, Vue, Inject } from 'vue-property-decorator';

import { numeric, minValue, decimal } from 'vuelidate/lib/validators';
import dayjs from 'dayjs';
import { DATE_TIME_LONG_FORMAT } from '@/shared/date/filters';

import AlertService from '@/shared/alert/alert.service';

import WeatherDataPointService from '@/entities/weather-data-point/weather-data-point.service';
import { IWeatherDataPoint } from '@/shared/model/weather-data-point.model';

import CheckLineService from '@/entities/check-line/check-line.service';
import { ICheckLine } from '@/shared/model/check-line.model';

import { ICheckPoint, CheckPoint } from '@/shared/model/check-point.model';
import CheckPointService from './check-point.service';

const validations: any = {
  checkPoint: {
    order: {
      numeric,
      min: minValue(0),
    },
    dateTime: {},
    locationName: {},
    latDecimal: {},
    lngDecimal: {},
    distance: {
      decimal,
      min: minValue(0),
    },
    distanceDisplayedValue: {},
    link: {},
    alerts: {},
  },
};

@Component({
  validations,
})
export default class CheckPointUpdate extends Vue {
  @Inject('checkPointService') private checkPointService: () => CheckPointService;
  @Inject('alertService') private alertService: () => AlertService;

  public checkPoint: ICheckPoint = new CheckPoint();

  @Inject('weatherDataPointService') private weatherDataPointService: () => WeatherDataPointService;

  public weatherDataPoints: IWeatherDataPoint[] = [];

  @Inject('checkLineService') private checkLineService: () => CheckLineService;

  public checkLines: ICheckLine[] = [];
  public isSaving = false;
  public currentLanguage = '';

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.checkPointId) {
        vm.retrieveCheckPoint(to.params.checkPointId);
      }
      vm.initRelationships();
    });
  }

  created(): void {
    this.currentLanguage = this.$store.getters.currentLanguage;
    this.$store.watch(
      () => this.$store.getters.currentLanguage,
      () => {
        this.currentLanguage = this.$store.getters.currentLanguage;
      }
    );
  }

  public save(): void {
    this.isSaving = true;
    if (this.checkPoint.id) {
      this.checkPointService()
        .update(this.checkPoint)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = 'A CheckPoint is updated with identifier ' + param.id;
          return this.$root.$bvToast.toast(message.toString(), {
            toaster: 'b-toaster-top-center',
            title: 'Info',
            variant: 'info',
            solid: true,
            autoHideDelay: 5000,
          });
        })
        .catch(error => {
          this.isSaving = false;
          this.alertService().showHttpError(this, error.response);
        });
    } else {
      this.checkPointService()
        .create(this.checkPoint)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = 'A CheckPoint is created with identifier ' + param.id;
          this.$root.$bvToast.toast(message.toString(), {
            toaster: 'b-toaster-top-center',
            title: 'Success',
            variant: 'success',
            solid: true,
            autoHideDelay: 5000,
          });
        })
        .catch(error => {
          this.isSaving = false;
          this.alertService().showHttpError(this, error.response);
        });
    }
  }

  public convertDateTimeFromServer(date: Date): string {
    if (date && dayjs(date).isValid()) {
      return dayjs(date).format(DATE_TIME_LONG_FORMAT);
    }
    return null;
  }

  public updateInstantField(field, event) {
    if (event.target.value) {
      this.checkPoint[field] = dayjs(event.target.value, DATE_TIME_LONG_FORMAT);
    } else {
      this.checkPoint[field] = null;
    }
  }

  public updateZonedDateTimeField(field, event) {
    if (event.target.value) {
      this.checkPoint[field] = dayjs(event.target.value, DATE_TIME_LONG_FORMAT);
    } else {
      this.checkPoint[field] = null;
    }
  }

  public retrieveCheckPoint(checkPointId): void {
    this.checkPointService()
      .find(checkPointId)
      .then(res => {
        res.dateTime = new Date(res.dateTime);
        this.checkPoint = res;
      })
      .catch(error => {
        this.alertService().showHttpError(this, error.response);
      });
  }

  public previousState(): void {
    this.$router.go(-1);
  }

  public initRelationships(): void {
    this.weatherDataPointService()
      .retrieve()
      .then(res => {
        this.weatherDataPoints = res.data;
      });
    this.checkLineService()
      .retrieve()
      .then(res => {
        this.checkLines = res.data;
      });
  }
}
