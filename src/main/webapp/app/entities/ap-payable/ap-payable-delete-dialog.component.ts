import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IApPayable } from 'app/shared/model/ap-payable.model';
import { ApPayableService } from './ap-payable.service';

@Component({
  selector: 'jhi-ap-payable-delete-dialog',
  templateUrl: './ap-payable-delete-dialog.component.html'
})
export class ApPayableDeleteDialogComponent {
  apPayable: IApPayable;

  constructor(protected apPayableService: ApPayableService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.apPayableService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'apPayableListModification',
        content: 'Deleted an apPayable'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-ap-payable-delete-popup',
  template: ''
})
export class ApPayableDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ apPayable }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(ApPayableDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.apPayable = apPayable;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/ap-payable', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/ap-payable', { outlets: { popup: null } }]);
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
