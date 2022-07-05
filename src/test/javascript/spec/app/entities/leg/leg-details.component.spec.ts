/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import VueRouter from 'vue-router';

import * as config from '@/shared/config/config';
import LegDetailComponent from '@/entities/leg/leg-details.vue';
import LegClass from '@/entities/leg/leg-details.component';
import LegService from '@/entities/leg/leg.service';
import router from '@/router';
import AlertService from '@/shared/alert/alert.service';

const localVue = createLocalVue();
localVue.use(VueRouter);

config.initVueApp(localVue);
const store = config.initVueXStore(localVue);
localVue.component('font-awesome-icon', {});
localVue.component('router-link', {});

describe('Component Tests', () => {
  describe('Leg Management Detail Component', () => {
    let wrapper: Wrapper<LegClass>;
    let comp: LegClass;
    let legServiceStub: SinonStubbedInstance<LegService>;

    beforeEach(() => {
      legServiceStub = sinon.createStubInstance<LegService>(LegService);

      wrapper = shallowMount<LegClass>(LegDetailComponent, {
        store,
        localVue,
        router,
        provide: { legService: () => legServiceStub, alertService: () => new AlertService() },
      });
      comp = wrapper.vm;
    });

    describe('OnInit', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        const foundLeg = { id: 123 };
        legServiceStub.find.resolves(foundLeg);

        // WHEN
        comp.retrieveLeg(123);
        await comp.$nextTick();

        // THEN
        expect(comp.leg).toBe(foundLeg);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        const foundLeg = { id: 123 };
        legServiceStub.find.resolves(foundLeg);

        // WHEN
        comp.beforeRouteEnter({ params: { legId: 123 } }, null, cb => cb(comp));
        await comp.$nextTick();

        // THEN
        expect(comp.leg).toBe(foundLeg);
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
