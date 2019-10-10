import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { Eshipper1TestModule } from '../../../test.module';
import { ApPayableDeleteDialogComponent } from 'app/entities/ap-payable/ap-payable-delete-dialog.component';
import { ApPayableService } from 'app/entities/ap-payable/ap-payable.service';

describe('Component Tests', () => {
  describe('ApPayable Management Delete Component', () => {
    let comp: ApPayableDeleteDialogComponent;
    let fixture: ComponentFixture<ApPayableDeleteDialogComponent>;
    let service: ApPayableService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [Eshipper1TestModule],
        declarations: [ApPayableDeleteDialogComponent]
      })
        .overrideTemplate(ApPayableDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ApPayableDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ApPayableService);
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
