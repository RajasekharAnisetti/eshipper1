import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { Eshipper1TestModule } from '../../../test.module';
import { ApPayeeTypeDeleteDialogComponent } from 'app/entities/ap-payee-type/ap-payee-type-delete-dialog.component';
import { ApPayeeTypeService } from 'app/entities/ap-payee-type/ap-payee-type.service';

describe('Component Tests', () => {
  describe('ApPayeeType Management Delete Component', () => {
    let comp: ApPayeeTypeDeleteDialogComponent;
    let fixture: ComponentFixture<ApPayeeTypeDeleteDialogComponent>;
    let service: ApPayeeTypeService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [Eshipper1TestModule],
        declarations: [ApPayeeTypeDeleteDialogComponent]
      })
        .overrideTemplate(ApPayeeTypeDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ApPayeeTypeDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ApPayeeTypeService);
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
