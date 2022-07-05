/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import VueRouter from 'vue-router';

import * as config from '@/shared/config/config';
import WeatherDataPointDetailComponent from '@/entities/weather-data-point/weather-data-point-details.vue';
import WeatherDataPointClass from '@/entities/weather-data-point/weather-data-point-details.component';
import WeatherDataPointService from '@/entities/weather-data-point/weather-data-point.service';
import router from '@/router';
import AlertService from '@/shared/alert/alert.service';

const localVue = createLocalVue();
localVue.use(VueRouter);

config.initVueApp(localVue);
const store = config.initVueXStore(localVue);
localVue.component('font-awesome-icon', {});
localVue.component('router-link', {});

describe('Component Tests', () => {
  describe('WeatherDataPoint Management Detail Component', () => {
    let wrapper: Wrapper<WeatherDataPointClass>;
    let comp: WeatherDataPointClass;
    let weatherDataPointServiceStub: SinonStubbedInstance<WeatherDataPointService>;

    beforeEach(() => {
      weatherDataPointServiceStub = sinon.createStubInstance<WeatherDataPointService>(WeatherDataPointService);

      wrapper = shallowMount<WeatherDataPointClass>(WeatherDataPointDetailComponent, {
        store,
        localVue,
        router,
        provide: { weatherDataPointService: () => weatherDataPointServiceStub, alertService: () => new AlertService() },
      });
      comp = wrapper.vm;
    });

    describe('OnInit', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        const foundWeatherDataPoint = { id: 123 };
        weatherDataPointServiceStub.find.resolves(foundWeatherDataPoint);

        // WHEN
        comp.retrieveWeatherDataPoint(123);
        await comp.$nextTick();

        // THEN
        expect(comp.weatherDataPoint).toBe(foundWeatherDataPoint);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        const foundWeatherDataPoint = { id: 123 };
        weatherDataPointServiceStub.find.resolves(foundWeatherDataPoint);

        // WHEN
        comp.beforeRouteEnter({ params: { weatherDataPointId: 123 } }, null, cb => cb(comp));
        await comp.$nextTick();

        // THEN
        expect(comp.weatherDataPoint).toBe(foundWeatherDataPoint);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        comp.previousState();
        await comp.$nextTick();

        expect(comp.$router.currentRoute.fullPath).toContain('/');
      });
    });
  });
});
