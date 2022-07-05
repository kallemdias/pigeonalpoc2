import { Component, Vue, Inject } from 'vue-property-decorator';

import { IWeatherDataPoint } from '@/shared/model/weather-data-point.model';
import WeatherDataPointService from './weather-data-point.service';
import AlertService from '@/shared/alert/alert.service';

@Component
export default class WeatherDataPointDetails extends Vue {
  @Inject('weatherDataPointService') private weatherDataPointService: () => WeatherDataPointService;
  @Inject('alertService') private alertService: () => AlertService;

  public weatherDataPoint: IWeatherDataPoint = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.weatherDataPointId) {
        vm.retrieveWeatherDataPoint(to.params.weatherDataPointId);
      }
    });
  }

  public retrieveWeatherDataPoint(weatherDataPointId) {
    this.weatherDataPointService()
      .find(weatherDataPointId)
      .then(res => {
        this.weatherDataPoint = res;
      })
      .catch(error => {
        this.alertService().showHttpError(this, error.response);
      });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
