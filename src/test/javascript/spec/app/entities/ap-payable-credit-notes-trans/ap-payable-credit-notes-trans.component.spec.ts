import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { Eshipper1TestModule } from '../../../test.module';
import { ApPayableCreditNotesTransComponent } from 'app/entities/ap-payable-credit-notes-trans/ap-payable-credit-notes-trans.component';
import { ApPayableCreditNotesTransService } from 'app/entities/ap-payable-credit-notes-trans/ap-payable-credit-notes-trans.service';
import { ApPayableCreditNotesTrans } from 'app/shared/model/ap-payable-credit-notes-trans.model';

describe('Component Tests', () => {
  describe('ApPayableCreditNotesTrans Management Component', () => {
    let comp: ApPayableCreditNotesTransComponent;
    let fixture: ComponentFixture<ApPayableCreditNotesTransComponent>;
    let service: ApPayableCreditNotesTransService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [Eshipper1TestModule],
        declarations: [ApPayableCreditNotesTransComponent],
        providers: []
      })
        .overrideTemplate(ApPayableCreditNotesTransComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ApPayableCreditNotesTransComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ApPayableCreditNotesTransService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new ApPayableCreditNotesTrans(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.apPayableCreditNotesTrans[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
