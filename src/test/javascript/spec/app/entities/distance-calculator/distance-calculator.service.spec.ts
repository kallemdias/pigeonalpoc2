/* tslint:disable max-line-length */
import axios from 'axios';
import sinon from 'sinon';

import DistanceCalculatorService from '@/entities/distance-calculator/distance-calculator.service';
import { DistanceCalculator } from '@/shared/model/distance-calculator.model';
import { LatDirection } from '@/shared/model/enumerations/lat-direction.model';
import { LngDirection } from '@/shared/model/enumerations/lng-direction.model';

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
  describe('DistanceCalculator Service', () => {
    let service: DistanceCalculatorService;
    let elemDefault;

    beforeEach(() => {
      service = new DistanceCalculatorService();
      elemDefault = new DistanceCalculator(
        123,
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
        0,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA'
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

      it('should create a DistanceCalculator', async () => {
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

      it('should not create a DistanceCalculator', async () => {
        axiosStub.post.rejects(error);

        return service
          .create({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should update a DistanceCalculator', async () => {
        const returnedFromService = Object.assign(
          {
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
            distanceInMeters: 1,
            distanceInMetersDispVal: 'BBBBBB',
            depLink: 'BBBBBB',
            arrLink: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);
        axiosStub.put.resolves({ data: returnedFromService });

        return service.update(expected).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not update a DistanceCalculator', async () => {
        axiosStub.put.rejects(error);

        return service
          .update({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should partial update a DistanceCalculator', async () => {
        const patchObject = Object.assign(
          {
            depLatMin: 1,
            depLatDisplayedValue: 'BBBBBB',
            arrLatSec: 1,
            arrLatDirection: 'BBBBBB',
            arrLngSec: 1,
            arrLngDirection: 'BBBBBB',
            arrLatDisplayedValue: 'BBBBBB',
            arrLngDisplayedValue: 'BBBBBB',
          },
          new DistanceCalculator()
        );
        const returnedFromService = Object.assign(patchObject, elemDefault);

        const expected = Object.assign({}, returnedFromService);
        axiosStub.patch.resolves({ data: returnedFromService });

        return service.partialUpdate(patchObject).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not partial update a DistanceCalculator', async () => {
        axiosStub.patch.rejects(error);

        return service
          .partialUpdate({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should return a list of DistanceCalculator', async () => {
        const returnedFromService = Object.assign(
          {
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
            distanceInMeters: 1,
            distanceInMetersDispVal: 'BBBBBB',
            depLink: 'BBBBBB',
            arrLink: 'BBBBBB',
          },
          elemDefault
        );
        const expected = Object.assign({}, returnedFromService);
        axiosStub.get.resolves([returnedFromService]);
        return service.retrieve({ sort: {}, page: 0, size: 10 }).then(res => {
          expect(res).toContainEqual(expected);
        });
      });

      it('should not return a list of DistanceCalculator', async () => {
        axiosStub.get.rejects(error);

        return service
          .retrieve()
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should delete a DistanceCalculator', async () => {
        axiosStub.delete.resolves({ ok: true });
        return service.delete(123).then(res => {
          expect(res.ok).toBeTruthy();
        });
      });

      it('should not delete a DistanceCalculator', async () => {
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
