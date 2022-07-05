/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import VueRouter from 'vue-router';

import * as config from '@/shared/config/config';
import GpsCoordinateCheckDetailComponent from '@/entities/gps-coordinate-check/gps-coordinate-check-details.vue';
import GpsCoordinateCheckClass from '@/entities/gps-coordinate-check/gps-coordinate-check-details.component';
import GpsCoordinateCheckService from '@/entities/gps-coordinate-check/gps-coordinate-check.service';
import router from '@/router';
import AlertService from '@/shared/alert/alert.service';

const localVue = createLocalVue();
localVue.use(VueRouter);

config.initVueApp(localVue);
const store = config.initVueXStore(localVue);
localVue.component('font-awesome-icon', {});
localVue.component('router-link', {});

describe('Component Tests', () => {
  describe('GpsCoordinateCheck Management Detail Component', () => {
    let wrapper: Wrapper<GpsCoordinateCheckClass>;
    let comp: GpsCoordinateCheckClass;
    let gpsCoordinateCheckServiceStub: SinonStubbedInstance<GpsCoordinateCheckService>;

    beforeEach(() => {
      gpsCoordinateCheckServiceStub = sinon.createStubInstance<GpsCoordinateCheckService>(GpsCoordinateCheckService);

      wrapper = shallowMount<GpsCoordinateCheckClass>(GpsCoordinateCheckDetailComponent, {
        store,
        localVue,
        router,
        provide: { gpsCoordinateCheckService: () => gpsCoordinateCheckServiceStub, alertService: () => new AlertService() },
      });
      comp = wrapper.vm;
    });

    describe('OnInit', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        const foundGpsCoordinateCheck = { id: 123 };
        gpsCoordinateCheckServiceStub.find.resolves(foundGpsCoordinateCheck);

        // WHEN
        comp.retrieveGpsCoordinateCheck(123);
        await comp.$nextTick();

        // THEN
        expect(comp.gpsCoordinateCheck).toBe(foundGpsCoordinateCheck);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        const foundGpsCoordinateCheck = { id: 123 };
        gpsCoordinateCheckServiceStub.find.resolves(foundGpsCoordinateCheck);

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
