import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { Eshipper1TestModule } from '../../../test.module';
import { ApCategoryTypeDeleteDialogComponent } from 'app/entities/ap-category-type/ap-category-type-delete-dialog.component';
import { ApCategoryTypeService } from 'app/entities/ap-category-type/ap-category-type.service';

describe('Component Tests', () => {
  describe('ApCategoryType Management Delete Component', () => {
    let comp: ApCategoryTypeDeleteDialogComponent;
    let fixture: ComponentFixture<ApCategoryTypeDeleteDialogComponent>;
    let service: ApCategoryTypeService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [Eshipper1TestModule],
        declarations: [ApCategoryTypeDeleteDialogComponent]
      })
        .overrideTemplate(ApCategoryTypeDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ApCategoryTypeDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ApCategoryTypeService);
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
