import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { Eshipper1TestModule } from '../../../test.module';
import { ApPayableDetailComponent } from 'app/entities/ap-payable/ap-payable-detail.component';
import { ApPayable } from 'app/shared/model/ap-payable.model';

describe('Component Tests', () => {
  describe('ApPayable Management Detail Component', () => {
    let comp: ApPayableDetailComponent;
    let fixture: ComponentFixture<ApPayableDetailComponent>;
    const route = ({ data: of({ apPayable: new ApPayable(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [Eshipper1TestModule],
        declarations: [ApPayableDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(ApPayableDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ApPayableDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.apPayable).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
