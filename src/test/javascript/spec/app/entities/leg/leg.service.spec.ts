/* tslint:disable max-line-length */
import axios from 'axios';
import sinon from 'sinon';

import LegService from '@/entities/leg/leg.service';
import { Leg } from '@/shared/model/leg.model';
import { LatDirection } from '@/shared/model/enumerations/lat-direction.model';
import { LngDirection } from '@/shared/model/enumerations/lng-direction.model';
import { CheckPointDistance } from '@/shared/model/enumerations/check-point-distance.model';
import { CheckLinePointDistance } from '@/shared/model/enumerations/check-line-point-distance.model';
import { TimeWindow } from '@/shared/model/enumerations/time-window.model';

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
  describe('Leg Service', () => {
    let service: LegService;
    let elemDefault;

    beforeEach(() => {
      service = new LegService();
      elemDefault = new Leg(
        123,
        0,
        0,
        0,
        0,
        LatDirection.NORTH,
        0,
        0,
        0,
        LngDirection.EAST,
        'AAAAAAA',
        'AAAAAAA',
        0,
        0,
        0,
        0,
        0,
        LatDirection.NORTH,
        0,
        0,
        0,
        LngDirection.EAST,
        'AAAAAAA',
        'AAAAAAA',
        0,
        0,
        CheckPointDistance.ONEKM,
        CheckLinePointDistance.FIVEKM,
        TimeWindow.THREEH
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

      it('should create a Leg', async () => {
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

      it('should not create a Leg', async () => {
        axiosStub.post.rejects(error);

        return service
          .create({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should update a Leg', async () => {
        const returnedFromService = Object.assign(
          {
            order: 1,
            depLatDeg: 1,
            depLatMin: 1,
            depLatSec: 1,
            depLatDirection: 'BBBBBB',
            depLngDeg: 1,
            depLngMin: 1,
            depLngSec: 1,
            depLngDirection: 'BBBBBB',
            depLatDisplayedValue: 'BBBBBB',
            depLngDisplayedValue: 'BBBBBB',
            depLatDecimal: 1,
            depLngDecimal: 1,
            arrLatDeg: 1,
            arrLatMin: 1,
            arrLatSec: 1,
            arrLatDirection: 'BBBBBB',
            arrLngDeg: 1,
            arrLngMin: 1,
            arrLngSec: 1,
            arrLngDirection: 'BBBBBB',
            arrLatDisplayedValue: 'BBBBBB',
            arrLngDisplayedValue: 'BBBBBB',
            arrLatDecimal: 1,
            arrLngDecimal: 1,
            checkPointDistance: 'BBBBBB',
            checkLinePointDistance: 'BBBBBB',
            timeWindow: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);
        axiosStub.put.resolves({ data: returnedFromService });

        return service.update(expected).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not update a Leg', async () => {
        axiosStub.put.rejects(error);

        return service
          .update({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should partial update a Leg', async () => {
        const patchObject = Object.assign(
          {
            depLatSec: 1,
            depLatDirection: 'BBBBBB',
            depLngDeg: 1,
            depLngMin: 1,
            depLngSec: 1,
            depLatDisplayedValue: 'BBBBBB',
            depLngDisplayedValue: 'BBBBBB',
            depLatDecimal: 1,
            arrLatSec: 1,
            arrLngDeg: 1,
            arrLngMin: 1,
            arrLngSec: 1,
            arrLngDecimal: 1,
            checkPointDistance: 'BBBBBB',
            checkLinePointDistance: 'BBBBBB',
          },
          new Leg()
        );
        const returnedFromService = Object.assign(patchObject, elemDefault);

        const expected = Object.assign({}, returnedFromService);
        axiosStub.patch.resolves({ data: returnedFromService });

        return service.partialUpdate(patchObject).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not partial update a Leg', async () => {
        axiosStub.patch.rejects(error);

        return service
          .partialUpdate({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should return a list of Leg', async () => {
        const returnedFromService = Object.assign(
          {
            order: 1,
            depLatDeg: 1,
            depLatMin: 1,
            depLatSec: 1,
            depLatDirection: 'BBBBBB',
            depLngDeg: 1,
            depLngMin: 1,
            depLngSec: 1,
            depLngDirection: 'BBBBBB',
            depLatDisplayedValue: 'BBBBBB',
            depLngDisplayedValue: 'BBBBBB',
            depLatDecimal: 1,
            depLngDecimal: 1,
            arrLatDeg: 1,
            arrLatMin: 1,
            arrLatSec: 1,
            arrLatDirection: 'BBBBBB',
            arrLngDeg: 1,
            arrLngMin: 1,
            arrLngSec: 1,
            arrLngDirection: 'BBBBBB',
            arrLatDisplayedValue: 'BBBBBB',
            arrLngDisplayedValue: 'BBBBBB',
            arrLatDecimal: 1,
            arrLngDecimal: 1,
            checkPointDistance: 'BBBBBB',
            checkLinePointDistance: 'BBBBBB',
            timeWindow: 'BBBBBB',
          },
          elemDefault
        );
        const expected = Object.assign({}, returnedFromService);
        axiosStub.get.resolves([returnedFromService]);
        return service.retrieve({ sort: {}, page: 0, size: 10 }).then(res => {
          expect(res).toContainEqual(expected);
        });
      });

      it('should not return a list of Leg', async () => {
        axiosStub.get.rejects(error);

        return service
          .retrieve()
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should delete a Leg', async () => {
        axiosStub.delete.resolves({ ok: true });
        return service.delete(123).then(res => {
          expect(res.ok).toBeTruthy();
        });
      });

      it('should not delete a Leg', async () => {
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
