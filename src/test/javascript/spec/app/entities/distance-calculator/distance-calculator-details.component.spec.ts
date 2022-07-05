/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import VueRouter from 'vue-router';

import * as config from '@/shared/config/config';
import DistanceCalculatorDetailComponent from '@/entities/distance-calculator/distance-calculator-details.vue';
import DistanceCalculatorClass from '@/entities/distance-calculator/distance-calculator-details.component';
import DistanceCalculatorService from '@/entities/distance-calculator/distance-calculator.service';
import router from '@/router';
import AlertService from '@/shared/alert/alert.service';

const localVue = createLocalVue();
localVue.use(VueRouter);

config.initVueApp(localVue);
const store = config.initVueXStore(localVue);
localVue.component('font-awesome-icon', {});
localVue.component('router-link', {});

describe('Component Tests', () => {
  describe('DistanceCalculator Management Detail Component', () => {
    let wrapper: Wrapper<DistanceCalculatorClass>;
    let comp: DistanceCalculatorClass;
    let distanceCalculatorServiceStub: SinonStubbedInstance<DistanceCalculatorService>;

    beforeEach(() => {
      distanceCalculatorServiceStub = sinon.createStubInstance<DistanceCalculatorService>(DistanceCalculatorService);

      wrapper = shallowMount<DistanceCalculatorClass>(DistanceCalculatorDetailComponent, {
        store,
        localVue,
        router,
        provide: { distanceCalculatorService: () => distanceCalculatorServiceStub, alertService: () => new AlertService() },
      });
      comp = wrapper.vm;
    });

    describe('OnInit', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        const foundDistanceCalculator = { id: 123 };
        distanceCalculatorServiceStub.find.resolves(foundDistanceCalculator);

        // WHEN
        comp.retrieveDistanceCalculator(123);
        await comp.$nextTick();

        // THEN
        expect(comp.distanceCalculator).toBe(foundDistanceCalculator);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        const foundDistanceCalculator = { id: 123 };
        distanceCalculatorServiceStub.find.resolves(foundDistanceCalculator);

        // WHEN
        comp.beforeRouteEnter({ params: { distanceCalculatorId: 123 } }, null, cb => cb(comp));
        await comp.$nextTick();

        // THEN
        expect(comp.distanceCalculator).toBe(foundDistanceCalculator);
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
