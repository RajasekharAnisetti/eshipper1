import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { Eshipper1TestModule } from '../../../test.module';
import { ApPayeeComponent } from 'app/entities/ap-payee/ap-payee.component';
import { ApPayeeService } from 'app/entities/ap-payee/ap-payee.service';
import { ApPayee } from 'app/shared/model/ap-payee.model';

describe('Component Tests', () => {
  describe('ApPayee Management Component', () => {
    let comp: ApPayeeComponent;
    let fixture: ComponentFixture<ApPayeeComponent>;
    let service: ApPayeeService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [Eshipper1TestModule],
        declarations: [ApPayeeComponent],
        providers: []
      })
        .overrideTemplate(ApPayeeComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ApPayeeComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ApPayeeService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new ApPayee(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.apPayees[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
