/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import Router from 'vue-router';
import { ToastPlugin } from 'bootstrap-vue';

import * as config from '@/shared/config/config';
import GpsCoordinateCheckUpdateComponent from '@/entities/gps-coordinate-check/gps-coordinate-check-update.vue';
import GpsCoordinateCheckClass from '@/entities/gps-coordinate-check/gps-coordinate-check-update.component';
import GpsCoordinateCheckService from '@/entities/gps-coordinate-check/gps-coordinate-check.service';

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
  describe('GpsCoordinateCheck Management Update Component', () => {
    let wrapper: Wrapper<GpsCoordinateCheckClass>;
    let comp: GpsCoordinateCheckClass;
    let gpsCoordinateCheckServiceStub: SinonStubbedInstance<GpsCoordinateCheckService>;

    beforeEach(() => {
      gpsCoordinateCheckServiceStub = sinon.createStubInstance<GpsCoordinateCheckService>(GpsCoordinateCheckService);

      wrapper = shallowMount<GpsCoordinateCheckClass>(GpsCoordinateCheckUpdateComponent, {
        store,
        localVue,
        router,
        provide: {
          gpsCoordinateCheckService: () => gpsCoordinateCheckServiceStub,
          alertService: () => new AlertService(),
        },
      });
      comp = wrapper.vm;
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', async () => {
        // GIVEN
        const entity = { id: 123 };
        comp.gpsCoordinateCheck = entity;
        gpsCoordinateCheckServiceStub.update.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(gpsCoordinateCheckServiceStub.update.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        comp.gpsCoordinateCheck = entity;
        gpsCoordinateCheckServiceStub.create.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(gpsCoordinateCheckServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        const foundGpsCoordinateCheck = { id: 123 };
        gpsCoordinateCheckServiceStub.find.resolves(foundGpsCoordinateCheck);
        gpsCoordinateCheckServiceStub.retrieve.resolves([foundGpsCoordinateCheck]);

        // WHEN
        comp.beforeRouteEnter({ params: { gpsCoordinateCheckId: 123 } }, null, cb => cb(comp));
        await comp.$nextTick();

        // THEN
        expect(comp.gpsCoordinateCheck).toBe(foundGpsCoordinateCheck);
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
