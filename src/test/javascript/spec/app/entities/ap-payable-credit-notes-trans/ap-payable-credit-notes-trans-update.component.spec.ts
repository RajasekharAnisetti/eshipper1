import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { Eshipper1TestModule } from '../../../test.module';
import { ApPayableCreditNotesTransUpdateComponent } from 'app/entities/ap-payable-credit-notes-trans/ap-payable-credit-notes-trans-update.component';
import { ApPayableCreditNotesTransService } from 'app/entities/ap-payable-credit-notes-trans/ap-payable-credit-notes-trans.service';
import { ApPayableCreditNotesTrans } from 'app/shared/model/ap-payable-credit-notes-trans.model';

describe('Component Tests', () => {
  describe('ApPayableCreditNotesTrans Management Update Component', () => {
    let comp: ApPayableCreditNotesTransUpdateComponent;
    let fixture: ComponentFixture<ApPayableCreditNotesTransUpdateComponent>;
    let service: ApPayableCreditNotesTransService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [Eshipper1TestModule],
        declarations: [ApPayableCreditNotesTransUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(ApPayableCreditNotesTransUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ApPayableCreditNotesTransUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ApPayableCreditNotesTransService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new ApPayableCreditNotesTrans(123);
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new ApPayableCreditNotesTrans();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});
