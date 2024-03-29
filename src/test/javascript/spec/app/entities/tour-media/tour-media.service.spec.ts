import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { TourMediaService } from 'app/entities/tour-media/tour-media.service';
import { ITourMedia, TourMedia } from 'app/shared/model/tour-media.model';
import { MediaType } from 'app/shared/model/enumerations/media-type.model';

describe('Service Tests', () => {
  describe('TourMedia Service', () => {
    let injector: TestBed;
    let service: TourMediaService;
    let httpMock: HttpTestingController;
    let elemDefault: ITourMedia;
    let expectedResult: ITourMedia | ITourMedia[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(TourMediaService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new TourMedia(0, 'AAAAAAA', MediaType.AUDIO, 'image/png', 'AAAAAAA');
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign({}, elemDefault);

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a TourMedia', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new TourMedia()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a TourMedia', () => {
        const returnedFromService = Object.assign(
          {
            mediaUrl: 'BBBBBB',
            mediaType: 'BBBBBB',
            mediacontent: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of TourMedia', () => {
        const returnedFromService = Object.assign(
          {
            mediaUrl: 'BBBBBB',
            mediaType: 'BBBBBB',
            mediacontent: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a TourMedia', () => {
        service.delete(123).subscribe(resp => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: 'DELETE' });
        req.flush({ status: 200 });
        expect(expectedResult);
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
