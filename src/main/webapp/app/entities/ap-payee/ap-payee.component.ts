import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { filter, map } from 'rxjs/operators';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IApPayee } from 'app/shared/model/ap-payee.model';
import { AccountService } from 'app/core/auth/account.service';
import { ApPayeeService } from './ap-payee.service';

@Component({
  selector: 'jhi-ap-payee',
  templateUrl: './ap-payee.component.html'
})
export class ApPayeeComponent implements OnInit, OnDestroy {
  apPayees: IApPayee[];
  currentAccount: any;
  eventSubscriber: Subscription;

  constructor(
    protected apPayeeService: ApPayeeService,
    protected jhiAlertService: JhiAlertService,
    protected eventManager: JhiEventManager,
    protected accountService: AccountService
  ) {}

  loadAll() {
    this.apPayeeService
      .query()
      .pipe(
        filter((res: HttpResponse<IApPayee[]>) => res.ok),
        map((res: HttpResponse<IApPayee[]>) => res.body)
      )
      .subscribe(
        (res: IApPayee[]) => {
          this.apPayees = res;
        },
        (res: HttpErrorResponse) => this.onError(res.message)
      );
  }

  ngOnInit() {
    this.loadAll();
    this.accountService.identity().then(account => {
      this.currentAccount = account;
    });
    this.registerChangeInApPayees();
  }

  ngOnDestroy() {
    this.eventManager.destroy(this.eventSubscriber);
  }

  trackId(index: number, item: IApPayee) {
    return item.id;
  }

  registerChangeInApPayees() {
    this.eventSubscriber = this.eventManager.subscribe('apPayeeListModification', response => this.loadAll());
  }

  protected onError(errorMessage: string) {
    this.jhiAlertService.error(errorMessage, null, null);
  }
}
