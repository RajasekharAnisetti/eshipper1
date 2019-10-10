import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { Eshipper1TestModule } from '../../../test.module';
import { WoPaymentMethodDeleteDialogComponent } from 'app/entities/wo-payment-method/wo-payment-method-delete-dialog.component';
import { WoPaymentMethodService } from 'app/entities/wo-payment-method/wo-payment-method.service';

describe('Component Tests', () => {
  describe('WoPaymentMethod Management Delete Component', () => {
    let comp: WoPaymentMethodDeleteDialogComponent;
    let fixture: ComponentFixture<WoPaymentMethodDeleteDialogComponent>;
    let service: WoPaymentMethodService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [Eshipper1TestModule],
        declarations: [WoPaymentMethodDeleteDialogComponent]
      })
        .overrideTemplate(WoPaymentMethodDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(WoPaymentMethodDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(WoPaymentMethodService);
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
