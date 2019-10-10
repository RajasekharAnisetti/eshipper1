import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { Eshipper1TestModule } from '../../../test.module';
import { ApPayeeDetailComponent } from 'app/entities/ap-payee/ap-payee-detail.component';
import { ApPayee } from 'app/shared/model/ap-payee.model';

describe('Component Tests', () => {
  describe('ApPayee Management Detail Component', () => {
    let comp: ApPayeeDetailComponent;
    let fixture: ComponentFixture<ApPayeeDetailComponent>;
    const route = ({ data: of({ apPayee: new ApPayee(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [Eshipper1TestModule],
        declarations: [ApPayeeDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(ApPayeeDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ApPayeeDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.apPayee).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
