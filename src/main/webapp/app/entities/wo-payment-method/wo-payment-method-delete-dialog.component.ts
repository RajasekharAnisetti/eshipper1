import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IWoPaymentMethod } from 'app/shared/model/wo-payment-method.model';
import { WoPaymentMethodService } from './wo-payment-method.service';

@Component({
  selector: 'jhi-wo-payment-method-delete-dialog',
  templateUrl: './wo-payment-method-delete-dialog.component.html'
})
export class WoPaymentMethodDeleteDialogComponent {
  woPaymentMethod: IWoPaymentMethod;

  constructor(
    protected woPaymentMethodService: WoPaymentMethodService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.woPaymentMethodService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'woPaymentMethodListModification',
        content: 'Deleted an woPaymentMethod'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-wo-payment-method-delete-popup',
  template: ''
})
export class WoPaymentMethodDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ woPaymentMethod }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(WoPaymentMethodDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.woPaymentMethod = woPaymentMethod;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/wo-payment-method', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/wo-payment-method', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          }
        );
      }, 0);
    });
  }

  ngOnDestroy() {
    this.ngbModalRef = null;
  }
}
