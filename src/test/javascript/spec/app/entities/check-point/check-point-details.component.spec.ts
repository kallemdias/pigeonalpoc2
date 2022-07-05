/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import VueRouter from 'vue-router';

import * as config from '@/shared/config/config';
import CheckPointDetailComponent from '@/entities/check-point/check-point-details.vue';
import CheckPointClass from '@/entities/check-point/check-point-details.component';
import CheckPointService from '@/entities/check-point/check-point.service';
import router from '@/router';
import AlertService from '@/shared/alert/alert.service';

const localVue = createLocalVue();
localVue.use(VueRouter);

config.initVueApp(localVue);
const store = config.initVueXStore(localVue);
localVue.component('font-awesome-icon', {});
localVue.component('router-link', {});

describe('Component Tests', () => {
  describe('CheckPoint Management Detail Component', () => {
    let wrapper: Wrapper<CheckPointClass>;
    let comp: CheckPointClass;
    let checkPointServiceStub: SinonStubbedInstance<CheckPointService>;

    beforeEach(() => {
      checkPointServiceStub = sinon.createStubInstance<CheckPointService>(CheckPointService);

      wrapper = shallowMount<CheckPointClass>(CheckPointDetailComponent, {
        store,
        localVue,
        router,
        provide: { checkPointService: () => checkPointServiceStub, alertService: () => new AlertService() },
      });
      comp = wrapper.vm;
    });

    describe('OnInit', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        const foundCheckPoint = { id: 123 };
        checkPointServiceStub.find.resolves(foundCheckPoint);

        // WHEN
        comp.retrieveCheckPoint(123);
        await comp.$nextTick();

        // THEN
        expect(comp.checkPoint).toBe(foundCheckPoint);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        const foundCheckPoint = { id: 123 };
        checkPointServiceStub.find.resolves(foundCheckPoint);

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
