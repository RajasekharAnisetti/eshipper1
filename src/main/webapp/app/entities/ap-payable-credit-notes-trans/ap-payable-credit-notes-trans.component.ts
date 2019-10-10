import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { filter, map } from 'rxjs/operators';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IApPayableCreditNotesTrans } from 'app/shared/model/ap-payable-credit-notes-trans.model';
import { AccountService } from 'app/core/auth/account.service';
import { ApPayableCreditNotesTransService } from './ap-payable-credit-notes-trans.service';

@Component({
  selector: 'jhi-ap-payable-credit-notes-trans',
  templateUrl: './ap-payable-credit-notes-trans.component.html'
})
export class ApPayableCreditNotesTransComponent implements OnInit, OnDestroy {
  apPayableCreditNotesTrans: IApPayableCreditNotesTrans[];
  currentAccount: any;
  eventSubscriber: Subscription;

  constructor(
    protected apPayableCreditNotesTransService: ApPayableCreditNotesTransService,
    protected jhiAlertService: JhiAlertService,
    protected eventManager: JhiEventManager,
    protected accountService: AccountService
  ) {}

  loadAll() {
    this.apPayableCreditNotesTransService
      .query()
      .pipe(
        filter((res: HttpResponse<IApPayableCreditNotesTrans[]>) => res.ok),
        map((res: HttpResponse<IApPayableCreditNotesTrans[]>) => res.body)
      )
      .subscribe(
        (res: IApPayableCreditNotesTrans[]) => {
          this.apPayableCreditNotesTrans = res;
        },
        (res: HttpErrorResponse) => this.onError(res.message)
      );
  }

  ngOnInit() {
    this.loadAll();
    this.accountService.identity().then(account => {
      this.currentAccount = account;
    });
    this.registerChangeInApPayableCreditNotesTrans();
  }

  ngOnDestroy() {
    this.eventManager.destroy(this.eventSubscriber);
  }

  trackId(index: number, item: IApPayableCreditNotesTrans) {
    return item.id;
  }

  registerChangeInApPayableCreditNotesTrans() {
    this.eventSubscriber = this.eventManager.subscribe('apPayableCreditNotesTransListModification', response => this.loadAll());
  }

  protected onError(errorMessage: string) {
    this.jhiAlertService.error(errorMessage, null, null);
  }
}
