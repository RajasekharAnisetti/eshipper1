import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { Eshipper1TestModule } from '../../../test.module';
import { ApPayableCreditNotesTransDetailComponent } from 'app/entities/ap-payable-credit-notes-trans/ap-payable-credit-notes-trans-detail.component';
import { ApPayableCreditNotesTrans } from 'app/shared/model/ap-payable-credit-notes-trans.model';

describe('Component Tests', () => {
  describe('ApPayableCreditNotesTrans Management Detail Component', () => {
    let comp: ApPayableCreditNotesTransDetailComponent;
    let fixture: ComponentFixture<ApPayableCreditNotesTransDetailComponent>;
    const route = ({ data: of({ apPayableCreditNotesTrans: new ApPayableCreditNotesTrans(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [Eshipper1TestModule],
        declarations: [ApPayableCreditNotesTransDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(ApPayableCreditNotesTransDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ApPayableCreditNotesTransDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.apPayableCreditNotesTrans).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
