/* tslint:disable max-line-length */
import axios from 'axios';
import sinon from 'sinon';

import GpsCoordinateCheckService from '@/entities/gps-coordinate-check/gps-coordinate-check.service';
import { GpsCoordinateCheck } from '@/shared/model/gps-coordinate-check.model';
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
  describe('GpsCoordinateCheck Service', () => {
    let service: GpsCoordinateCheckService;
    let elemDefault;

    beforeEach(() => {
      service = new GpsCoordinateCheckService();
      elemDefault = new GpsCoordinateCheck(
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

      it('should create a GpsCoordinateCheck', async () => {
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

      it('should not create a GpsCoordinateCheck', async () => {
        axiosStub.post.rejects(error);

        return service
          .create({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should update a GpsCoordinateCheck', async () => {
        const returnedFromService = Object.assign(
          {
            latDeg: 1,
            latMin: 1,
            latSec: 1,
            latDirection: 'BBBBBB',
            lngDeg: 1,
            lngMin: 1,
            lngSec: 1,
            lngDirection: 'BBBBBB',
            latDisplayedValue: 'BBBBBB',
            lngDisplayedValue: 'BBBBBB',
            latDecimal: 1,
            lngDecimal: 1,
            link: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);
        axiosStub.put.resolves({ data: returnedFromService });

        return service.update(expected).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not update a GpsCoordinateCheck', async () => {
        axiosStub.put.rejects(error);

        return service
          .update({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should partial update a GpsCoordinateCheck', async () => {
        const patchObject = Object.assign(
          {
            latMin: 1,
            latDirection: 'BBBBBB',
            lngDeg: 1,
            lngDisplayedValue: 'BBBBBB',
            latDecimal: 1,
            lngDecimal: 1,
          },
          new GpsCoordinateCheck()
        );
        const returnedFromService = Object.assign(patchObject, elemDefault);

        const expected = Object.assign({}, returnedFromService);
        axiosStub.patch.resolves({ data: returnedFromService });

        return service.partialUpdate(patchObject).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not partial update a GpsCoordinateCheck', async () => {
        axiosStub.patch.rejects(error);

        return service
          .partialUpdate({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should return a list of GpsCoordinateCheck', async () => {
        const returnedFromService = Object.assign(
          {
            latDeg: 1,
            latMin: 1,
            latSec: 1,
            latDirection: 'BBBBBB',
            lngDeg: 1,
            lngMin: 1,
            lngSec: 1,
            lngDirection: 'BBBBBB',
            latDisplayedValue: 'BBBBBB',
            lngDisplayedValue: 'BBBBBB',
            latDecimal: 1,
            lngDecimal: 1,
            link: 'BBBBBB',
          },
          elemDefault
        );
        const expected = Object.assign({}, returnedFromService);
        axiosStub.get.resolves([returnedFromService]);
        return service.retrieve({ sort: {}, page: 0, size: 10 }).then(res => {
          expect(res).toContainEqual(expected);
        });
      });

      it('should not return a list of GpsCoordinateCheck', async () => {
        axiosStub.get.rejects(error);

        return service
          .retrieve()
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should delete a GpsCoordinateCheck', async () => {
        axiosStub.delete.resolves({ ok: true });
        return service.delete(123).then(res => {
          expect(res.ok).toBeTruthy();
        });
      });

      it('should not delete a GpsCoordinateCheck', async () => {
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
