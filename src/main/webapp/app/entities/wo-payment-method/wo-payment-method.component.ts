import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { filter, map } from 'rxjs/operators';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IWoPaymentMethod } from 'app/shared/model/wo-payment-method.model';
import { AccountService } from 'app/core/auth/account.service';
import { WoPaymentMethodService } from './wo-payment-method.service';

@Component({
  selector: 'jhi-wo-payment-method',
  templateUrl: './wo-payment-method.component.html'
})
export class WoPaymentMethodComponent implements OnInit, OnDestroy {
  woPaymentMethods: IWoPaymentMethod[];
  currentAccount: any;
  eventSubscriber: Subscription;

  constructor(
    protected woPaymentMethodService: WoPaymentMethodService,
    protected jhiAlertService: JhiAlertService,
    protected eventManager: JhiEventManager,
    protected accountService: AccountService
  ) {}

  loadAll() {
    this.woPaymentMethodService
      .query()
      .pipe(
        filter((res: HttpResponse<IWoPaymentMethod[]>) => res.ok),
        map((res: HttpResponse<IWoPaymentMethod[]>) => res.body)
      )
      .subscribe(
        (res: IWoPaymentMethod[]) => {
          this.woPaymentMethods = res;
        },
        (res: HttpErrorResponse) => this.onError(res.message)
      );
  }

  ngOnInit() {
    this.loadAll();
    this.accountService.identity().then(account => {
      this.currentAccount = account;
    });
    this.registerChangeInWoPaymentMethods();
  }

  ngOnDestroy() {
    this.eventManager.destroy(this.eventSubscriber);
  }

  trackId(index: number, item: IWoPaymentMethod) {
    return item.id;
  }

  registerChangeInWoPaymentMethods() {
    this.eventSubscriber = this.eventManager.subscribe('woPaymentMethodListModification', response => this.loadAll());
  }

  protected onError(errorMessage: string) {
    this.jhiAlertService.error(errorMessage, null, null);
  }
}
