import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { Eshipper1TestModule } from '../../../test.module';
import { ApPayableComponent } from 'app/entities/ap-payable/ap-payable.component';
import { ApPayableService } from 'app/entities/ap-payable/ap-payable.service';
import { ApPayable } from 'app/shared/model/ap-payable.model';

describe('Component Tests', () => {
  describe('ApPayable Management Component', () => {
    let comp: ApPayableComponent;
    let fixture: ComponentFixture<ApPayableComponent>;
    let service: ApPayableService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [Eshipper1TestModule],
        declarations: [ApPayableComponent],
        providers: []
      })
        .overrideTemplate(ApPayableComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ApPayableComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ApPayableService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new ApPayable(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.apPayables[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
