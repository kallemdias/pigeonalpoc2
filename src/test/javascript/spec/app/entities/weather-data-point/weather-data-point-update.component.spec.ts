/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import Router from 'vue-router';
import { ToastPlugin } from 'bootstrap-vue';

import * as config from '@/shared/config/config';
import WeatherDataPointUpdateComponent from '@/entities/weather-data-point/weather-data-point-update.vue';
import WeatherDataPointClass from '@/entities/weather-data-point/weather-data-point-update.component';
import WeatherDataPointService from '@/entities/weather-data-point/weather-data-point.service';

import RelevanceHeaderService from '@/entities/relevance-header/relevance-header.service';

import CheckPointService from '@/entities/check-point/check-point.service';
import AlertService from '@/shared/alert/alert.service';

const localVue = createLocalVue();

config.initVueApp(localVue);
const store = config.initVueXStore(localVue);
const router = new Router();
localVue.use(Router);
localVue.use(ToastPlugin);
localVue.component('font-awesome-icon', {});
localVue.component('b-input-group', {});
localVue.component('b-input-group-prepend', {});
localVue.component('b-form-datepicker', {});
localVue.component('b-form-input', {});

describe('Component Tests', () => {
  describe('WeatherDataPoint Management Update Component', () => {
    let wrapper: Wrapper<WeatherDataPointClass>;
    let comp: WeatherDataPointClass;
    let weatherDataPointServiceStub: SinonStubbedInstance<WeatherDataPointService>;

    beforeEach(() => {
      weatherDataPointServiceStub = sinon.createStubInstance<WeatherDataPointService>(WeatherDataPointService);

      wrapper = shallowMount<WeatherDataPointClass>(WeatherDataPointUpdateComponent, {
        store,
        localVue,
        router,
        provide: {
          weatherDataPointService: () => weatherDataPointServiceStub,
          alertService: () => new AlertService(),

          relevanceHeaderService: () =>
            sinon.createStubInstance<RelevanceHeaderService>(RelevanceHeaderService, {
              retrieve: sinon.stub().resolves({}),
            } as any),

          checkPointService: () =>
            sinon.createStubInstance<CheckPointService>(CheckPointService, {
              retrieve: sinon.stub().resolves({}),
            } as any),
        },
      });
      comp = wrapper.vm;
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', async () => {
        // GIVEN
        const entity = { id: 123 };
        comp.weatherDataPoint = entity;
        weatherDataPointServiceStub.update.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(weatherDataPointServiceStub.update.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        comp.weatherDataPoint = entity;
        weatherDataPointServiceStub.create.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(weatherDataPointServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        const foundWeatherDataPoint = { id: 123 };
        weatherDataPointServiceStub.find.resolves(foundWeatherDataPoint);
        weatherDataPointServiceStub.retrieve.resolves([foundWeatherDataPoint]);

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
