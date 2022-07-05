/* tslint:disable max-line-length */
import axios from 'axios';
import sinon from 'sinon';

import WeatherDataPointService from '@/entities/weather-data-point/weather-data-point.service';
import { WeatherDataPoint } from '@/shared/model/weather-data-point.model';
import { WeatherProperty } from '@/shared/model/enumerations/weather-property.model';

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
  describe('WeatherDataPoint Service', () => {
    let service: WeatherDataPointService;
    let elemDefault;

    beforeEach(() => {
      service = new WeatherDataPointService();
      elemDefault = new WeatherDataPoint(
        123,
        WeatherProperty.TIME,
        'AAAAAAA',
        false,
        'AAAAAAA',
        false,
        'AAAAAAA',
        false,
        'AAAAAAA',
        false,
        'AAAAAAA',
        false,
        'AAAAAAA',
        false,
        'AAAAAAA',
        false,
        'AAAAAAA',
        false,
        'AAAAAAA',
        false,
        'AAAAAAA',
        false,
        'AAAAAAA',
        false,
        'AAAAAAA',
        false,
        'AAAAAAA',
        false,
        'AAAAAAA',
        false,
        'AAAAAAA',
        false,
        'AAAAAAA',
        false,
        'AAAAAAA',
        false,
        'AAAAAAA',
        false,
        'AAAAAAA',
        false,
        'AAAAAAA',
        false,
        'AAAAAAA',
        false,
        'AAAAAAA',
        false,
        'AAAAAAA',
        false,
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

      it('should create a WeatherDataPoint', async () => {
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

      it('should not create a WeatherDataPoint', async () => {
        axiosStub.post.rejects(error);

        return service
          .create({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should update a WeatherDataPoint', async () => {
        const returnedFromService = Object.assign(
          {
            property: 'BBBBBB',
            midNight: 'BBBBBB',
            migNightRelevance: true,
            one: 'BBBBBB',
            oneRelevance: true,
            two: 'BBBBBB',
            twoRelevance: true,
            three: 'BBBBBB',
            threeRelevance: true,
            four: 'BBBBBB',
            fourRelevance: true,
            five: 'BBBBBB',
            fiveRelevance: true,
            six: 'BBBBBB',
            sixRelevance: true,
            seven: 'BBBBBB',
            sevenRelevance: true,
            eight: 'BBBBBB',
            eightRelevance: true,
            nine: 'BBBBBB',
            nineRelevance: true,
            ten: 'BBBBBB',
            tenRelevance: true,
            eleven: 'BBBBBB',
            elevenRelevance: true,
            twelve: 'BBBBBB',
            twelveRelevance: true,
            thirteen: 'BBBBBB',
            thirteenRelevance: true,
            fourteen: 'BBBBBB',
            fourteenRelevance: true,
            fifteen: 'BBBBBB',
            fifteenRelevance: true,
            sixteen: 'BBBBBB',
            sixteenRelevance: true,
            seventeen: 'BBBBBB',
            seventeenRelevance: true,
            eighteen: 'BBBBBB',
            eighteenRelevance: true,
            nineteen: 'BBBBBB',
            nineteenRelevance: true,
            twenty: 'BBBBBB',
            twentyRelevance: true,
            twentyOne: 'BBBBBB',
            twentyOneRelevance: true,
            twentyTwo: 'BBBBBB',
            twentyTwoRelevance: true,
            twentyThree: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);
        axiosStub.put.resolves({ data: returnedFromService });

        return service.update(expected).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not update a WeatherDataPoint', async () => {
        axiosStub.put.rejects(error);

        return service
          .update({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should partial update a WeatherDataPoint', async () => {
        const patchObject = Object.assign(
          {
            midNight: 'BBBBBB',
            one: 'BBBBBB',
            two: 'BBBBBB',
            twoRelevance: true,
            three: 'BBBBBB',
            fourRelevance: true,
            five: 'BBBBBB',
            fiveRelevance: true,
            six: 'BBBBBB',
            sixRelevance: true,
            seven: 'BBBBBB',
            eightRelevance: true,
            nine: 'BBBBBB',
            ten: 'BBBBBB',
            eleven: 'BBBBBB',
            elevenRelevance: true,
            fourteenRelevance: true,
            fifteen: 'BBBBBB',
            fifteenRelevance: true,
            sixteen: 'BBBBBB',
            sixteenRelevance: true,
            seventeen: 'BBBBBB',
            seventeenRelevance: true,
            eighteenRelevance: true,
            nineteenRelevance: true,
            twenty: 'BBBBBB',
            twentyOne: 'BBBBBB',
            twentyTwo: 'BBBBBB',
          },
          new WeatherDataPoint()
        );
        const returnedFromService = Object.assign(patchObject, elemDefault);

        const expected = Object.assign({}, returnedFromService);
        axiosStub.patch.resolves({ data: returnedFromService });

        return service.partialUpdate(patchObject).then(res => {
          expect(res).toMatchObject(expected);
        });
      });

      it('should not partial update a WeatherDataPoint', async () => {
        axiosStub.patch.rejects(error);

        return service
          .partialUpdate({})
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should return a list of WeatherDataPoint', async () => {
        const returnedFromService = Object.assign(
          {
            property: 'BBBBBB',
            midNight: 'BBBBBB',
            migNightRelevance: true,
            one: 'BBBBBB',
            oneRelevance: true,
            two: 'BBBBBB',
            twoRelevance: true,
            three: 'BBBBBB',
            threeRelevance: true,
            four: 'BBBBBB',
            fourRelevance: true,
            five: 'BBBBBB',
            fiveRelevance: true,
            six: 'BBBBBB',
            sixRelevance: true,
            seven: 'BBBBBB',
            sevenRelevance: true,
            eight: 'BBBBBB',
            eightRelevance: true,
            nine: 'BBBBBB',
            nineRelevance: true,
            ten: 'BBBBBB',
            tenRelevance: true,
            eleven: 'BBBBBB',
            elevenRelevance: true,
            twelve: 'BBBBBB',
            twelveRelevance: true,
            thirteen: 'BBBBBB',
            thirteenRelevance: true,
            fourteen: 'BBBBBB',
            fourteenRelevance: true,
            fifteen: 'BBBBBB',
            fifteenRelevance: true,
            sixteen: 'BBBBBB',
            sixteenRelevance: true,
            seventeen: 'BBBBBB',
            seventeenRelevance: true,
            eighteen: 'BBBBBB',
            eighteenRelevance: true,
            nineteen: 'BBBBBB',
            nineteenRelevance: true,
            twenty: 'BBBBBB',
            twentyRelevance: true,
            twentyOne: 'BBBBBB',
            twentyOneRelevance: true,
            twentyTwo: 'BBBBBB',
            twentyTwoRelevance: true,
            twentyThree: 'BBBBBB',
          },
          elemDefault
        );
        const expected = Object.assign({}, returnedFromService);
        axiosStub.get.resolves([returnedFromService]);
        return service.retrieve({ sort: {}, page: 0, size: 10 }).then(res => {
          expect(res).toContainEqual(expected);
        });
      });

      it('should not return a list of WeatherDataPoint', async () => {
        axiosStub.get.rejects(error);

        return service
          .retrieve()
          .then()
          .catch(err => {
            expect(err).toMatchObject(error);
          });
      });

      it('should delete a WeatherDataPoint', async () => {
        axiosStub.delete.resolves({ ok: true });
        return service.delete(123).then(res => {
          expect(res.ok).toBeTruthy();
        });
      });

      it('should not delete a WeatherDataPoint', async () => {
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
