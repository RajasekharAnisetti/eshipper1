import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { Eshipper1TestModule } from '../../../test.module';
import { ApPayeeTypeComponent } from 'app/entities/ap-payee-type/ap-payee-type.component';
import { ApPayeeTypeService } from 'app/entities/ap-payee-type/ap-payee-type.service';
import { ApPayeeType } from 'app/shared/model/ap-payee-type.model';

describe('Component Tests', () => {
  describe('ApPayeeType Management Component', () => {
    let comp: ApPayeeTypeComponent;
    let fixture: ComponentFixture<ApPayeeTypeComponent>;
    let service: ApPayeeTypeService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [Eshipper1TestModule],
        declarations: [ApPayeeTypeComponent],
        providers: []
      })
        .overrideTemplate(ApPayeeTypeComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ApPayeeTypeComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ApPayeeTypeService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new ApPayeeType(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.apPayeeTypes[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
