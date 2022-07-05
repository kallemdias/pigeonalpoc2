/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import VueRouter from 'vue-router';

import * as config from '@/shared/config/config';
import RelevanceHeaderDetailComponent from '@/entities/relevance-header/relevance-header-details.vue';
import RelevanceHeaderClass from '@/entities/relevance-header/relevance-header-details.component';
import RelevanceHeaderService from '@/entities/relevance-header/relevance-header.service';
import router from '@/router';
import AlertService from '@/shared/alert/alert.service';

const localVue = createLocalVue();
localVue.use(VueRouter);

config.initVueApp(localVue);
const store = config.initVueXStore(localVue);
localVue.component('font-awesome-icon', {});
localVue.component('router-link', {});

describe('Component Tests', () => {
  describe('RelevanceHeader Management Detail Component', () => {
    let wrapper: Wrapper<RelevanceHeaderClass>;
    let comp: RelevanceHeaderClass;
    let relevanceHeaderServiceStub: SinonStubbedInstance<RelevanceHeaderService>;

    beforeEach(() => {
      relevanceHeaderServiceStub = sinon.createStubInstance<RelevanceHeaderService>(RelevanceHeaderService);

      wrapper = shallowMount<RelevanceHeaderClass>(RelevanceHeaderDetailComponent, {
        store,
        localVue,
        router,
        provide: { relevanceHeaderService: () => relevanceHeaderServiceStub, alertService: () => new AlertService() },
      });
      comp = wrapper.vm;
    });

    describe('OnInit', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        const foundRelevanceHeader = { id: 123 };
        relevanceHeaderServiceStub.find.resolves(foundRelevanceHeader);

        // WHEN
        comp.retrieveRelevanceHeader(123);
        await comp.$nextTick();

        // THEN
        expect(comp.relevanceHeader).toBe(foundRelevanceHeader);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        const foundRelevanceHeader = { id: 123 };
        relevanceHeaderServiceStub.find.resolves(foundRelevanceHeader);

        // WHEN
        comp.beforeRouteEnter({ params: { relevanceHeaderId: 123 } }, null, cb => cb(comp));
        await comp.$nextTick();

        // THEN
        expect(comp.relevanceHeader).toBe(foundRelevanceHeader);
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
