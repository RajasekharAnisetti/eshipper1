import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { Eshipper1TestModule } from '../../../test.module';
import { ApPayableCreditNotesTransDeleteDialogComponent } from 'app/entities/ap-payable-credit-notes-trans/ap-payable-credit-notes-trans-delete-dialog.component';
import { ApPayableCreditNotesTransService } from 'app/entities/ap-payable-credit-notes-trans/ap-payable-credit-notes-trans.service';

describe('Component Tests', () => {
  describe('ApPayableCreditNotesTrans Management Delete Component', () => {
    let comp: ApPayableCreditNotesTransDeleteDialogComponent;
    let fixture: ComponentFixture<ApPayableCreditNotesTransDeleteDialogComponent>;
    let service: ApPayableCreditNotesTransService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [Eshipper1TestModule],
        declarations: [ApPayableCreditNotesTransDeleteDialogComponent]
      })
        .overrideTemplate(ApPayableCreditNotesTransDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ApPayableCreditNotesTransDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ApPayableCreditNotesTransService);
      mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
      mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
    });

    describe('confirmDelete', () => {
      it('Should call delete service on confirmDelete', inject(
        [],
        fakeAsync(() => {
          // GIVEN
          spyOn(service, 'delete').and.returnValue(of({}));

          // WHEN
          comp.confirmDelete(123);
          tick();

          // THEN
          expect(service.delete).toHaveBeenCalledWith(123);
          expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
          expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
        })
      ));
    });
  });
});
