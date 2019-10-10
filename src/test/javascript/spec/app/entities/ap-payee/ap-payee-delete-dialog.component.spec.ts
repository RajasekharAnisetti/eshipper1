import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { Eshipper1TestModule } from '../../../test.module';
import { ApPayeeDeleteDialogComponent } from 'app/entities/ap-payee/ap-payee-delete-dialog.component';
import { ApPayeeService } from 'app/entities/ap-payee/ap-payee.service';

describe('Component Tests', () => {
  describe('ApPayee Management Delete Component', () => {
    let comp: ApPayeeDeleteDialogComponent;
    let fixture: ComponentFixture<ApPayeeDeleteDialogComponent>;
    let service: ApPayeeService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [Eshipper1TestModule],
        declarations: [ApPayeeDeleteDialogComponent]
      })
        .overrideTemplate(ApPayeeDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ApPayeeDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ApPayeeService);
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
