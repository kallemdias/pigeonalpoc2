/* tslint:disable max-line-length */
import axios from 'axios';
import sinon from 'sinon';

import RelevanceHeaderService from '@/entities/relevance-header/relevance-header.service';
import { RelevanceHeader } from '@/shared/model/relevance-header.model';

const error = {
  response: {
    status: null,
    data: {
      type: null,
    },
  },
};

const axiosStub = {
  get: sinon.stub(axios, 'get'),
  post: sinon.stub(axios, 'post'),
  put: sinon.stub(axios, 'put'),
  patch: sinon.stub(axios, 'patch'),
  delete: sinon.stub(axios, 'delete'),
};

describe('Service Tests', () => {
  describe('RelevanceHeader Service', () => {
    let service: RelevanceHeaderService;
    let elemDefault;

    beforeEach(() => {
      service = new RelevanceHeaderService();
      elemDefault = new RelevanceHeader(
        123,
        false,
        false,
        false,
        false,
        false,
        false,
        false,
        false,
        false,
        false,
        false,
        false,
        false,
        false,
        false,
        false,
        false,
        false,
        false,
        false,
        false,
        false,
        false,
        false
      );
    });

    describe('Service methods', () => {
      it('should find an element', async () => {
        const returnedFromService = Object.assign({}, elemDefault);
        axiosStub.get.resolves({ data: returnedFromService });

        return service.find(123).then(res => {
          expect(res).toMatchObject(elemDefault);
        });
      });

      it('should not find an element', async () => {
        axiosStub.get.rejects(error);
        return service
          .find(123)
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should create a RelevanceHeader', async () => {
        const returnedFromService = Object.assign(
          {
            id: 123,
          },
          elemDefault
        );
        const expected = Object.assign({}, returnedFromService);

        axiosStub.post.resolves({ data: returnedFromService });
        return service.create({}).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not create a RelevanceHeader', async () => {
        axiosStub.post.rejects(error);

        return service
          .create({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should update a RelevanceHeader', async () => {
        const returnedFromService = Object.assign(
          {
            midnight: true,
            one: true,
            two: true,
            three: true,
            four: true,
            five: true,
            six: true,
            seven: true,
            eight: true,
            nine: true,
            ten: true,
            eleven: true,
            twelve: true,
            thirteen: true,
            fourteen: true,
            fifteen: true,
            sixteen: true,
            seventeen: true,
            eighteen: true,
            nineteen: true,
            twenty: true,
            twentyOne: true,
            twentyTwo: true,
            twentyThree: true,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);
        axiosStub.put.resolves({ data: returnedFromService });

        return service.update(expected).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not update a RelevanceHeader', async () => {
        axiosStub.put.rejects(error);

        return service
          .update({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should partial update a RelevanceHeader', async () => {
        const patchObject = Object.assign(
          {
            midnight: true,
            four: true,
            five: true,
            six: true,
            fourteen: true,
            fifteen: true,
            eighteen: true,
            nineteen: true,
            twenty: true,
            twentyOne: true,
            twentyTwo: true,
          },
          new RelevanceHeader()
        );
        const returnedFromService = Object.assign(patchObject, elemDefault);

        const expected = Object.assign({}, returnedFromService);
        axiosStub.patch.resolves({ data: returnedFromService });

        return service.partialUpdate(patchObject).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not partial update a RelevanceHeader', async () => {
        axiosStub.patch.rejects(error);

        return service
          .partialUpdate({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should return a list of RelevanceHeader', async () => {
        const returnedFromService = Object.assign(
          {
            midnight: true,
            one: true,
            two: true,
            three: true,
            four: true,
            five: true,
            six: true,
            seven: true,
            eight: true,
            nine: true,
            ten: true,
            eleven: true,
            twelve: true,
            thirteen: true,
            fourteen: true,
            fifteen: true,
            sixteen: true,
            seventeen: true,
            eighteen: true,
            nineteen: true,
            twenty: true,
            twentyOne: true,
            twentyTwo: true,
            twentyThree: true,
          },
          elemDefault
        );
        const expected = Object.assign({}, returnedFromService);
        axiosStub.get.resolves([returnedFromService]);
        return service.retrieve({ sort: {}, page: 0, size: 10 }).then(res => {
          expect(res).toContainEqual(expected);
        });
      });

      it('should not return a list of RelevanceHeader', async () => {
        axiosStub.get.rejects(error);

        return service
          .retrieve()
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should delete a RelevanceHeader', async () => {
        axiosStub.delete.resolves({ ok: true });
        return service.delete(123).then(res => {
          expect(res.ok).toBeTruthy();
        });
      });

      it('should not delete a RelevanceHeader', async () => {
        axiosStub.delete.rejects(error);

        return service
          .delete(123)
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });
    });
  });
});
