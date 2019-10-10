import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IApPayee } from 'app/shared/model/ap-payee.model';
import { ApPayeeService } from './ap-payee.service';

@Component({
  selector: 'jhi-ap-payee-delete-dialog',
  templateUrl: './ap-payee-delete-dialog.component.html'
})
export class ApPayeeDeleteDialogComponent {
  apPayee: IApPayee;

  constructor(protected apPayeeService: ApPayeeService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.apPayeeService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'apPayeeListModification',
        content: 'Deleted an apPayee'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-ap-payee-delete-popup',
  template: ''
})
export class ApPayeeDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ apPayee }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(ApPayeeDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.apPayee = apPayee;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/ap-payee', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/ap-payee', { outlets: { popup: null } }]);
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
