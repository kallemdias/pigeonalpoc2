/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import Router from 'vue-router';
import { ToastPlugin } from 'bootstrap-vue';

import dayjs from 'dayjs';
import { DATE_TIME_LONG_FORMAT } from '@/shared/date/filters';

import * as config from '@/shared/config/config';
import CheckPointUpdateComponent from '@/entities/check-point/check-point-update.vue';
import CheckPointClass from '@/entities/check-point/check-point-update.component';
import CheckPointService from '@/entities/check-point/check-point.service';

import WeatherDataPointService from '@/entities/weather-data-point/weather-data-point.service';

import CheckLineService from '@/entities/check-line/check-line.service';
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
  describe('CheckPoint Management Update Component', () => {
    let wrapper: Wrapper<CheckPointClass>;
    let comp: CheckPointClass;
    let checkPointServiceStub: SinonStubbedInstance<CheckPointService>;

    beforeEach(() => {
      checkPointServiceStub = sinon.createStubInstance<CheckPointService>(CheckPointService);

      wrapper = shallowMount<CheckPointClass>(CheckPointUpdateComponent, {
        store,
        localVue,
        router,
        provide: {
          checkPointService: () => checkPointServiceStub,
          alertService: () => new AlertService(),

          weatherDataPointService: () =>
            sinon.createStubInstance<WeatherDataPointService>(WeatherDataPointService, {
              retrieve: sinon.stub().resolves({}),
            } as any),

          checkLineService: () =>
            sinon.createStubInstance<CheckLineService>(CheckLineService, {
              retrieve: sinon.stub().resolves({}),
            } as any),
        },
      });
      comp = wrapper.vm;
    });

    describe('load', () => {
      it('Should convert date from string', () => {
        // GIVEN
        const date = new Date('2019-10-15T11:42:02Z');

        // WHEN
        const convertedDate = comp.convertDateTimeFromServer(date);

        // THEN
        expect(convertedDate).toEqual(dayjs(date).format(DATE_TIME_LONG_FORMAT));
      });

      it('Should not convert date if date is not present', () => {
        expect(comp.convertDateTimeFromServer(null)).toBeNull();
      });
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', async () => {
        // GIVEN
        const entity = { id: 123 };
        comp.checkPoint = entity;
        checkPointServiceStub.update.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(checkPointServiceStub.update.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        comp.checkPoint = entity;
        checkPointServiceStub.create.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(checkPointServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        const foundCheckPoint = { id: 123 };
        checkPointServiceStub.find.resolves(foundCheckPoint);
        checkPointServiceStub.retrieve.resolves([foundCheckPoint]);

        // WHEN
        comp.beforeRouteEnter({ params: { checkPointId: 123 } }, null, cb => cb(comp));
        await comp.$nextTick();

        // THEN
        expect(comp.checkPoint).toBe(foundCheckPoint);
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
