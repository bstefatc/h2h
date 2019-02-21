/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { H2HTestModule } from '../../../test.module';
import { ComparisonHoldingDeleteDialogComponent } from 'app/entities/comparison-holding/comparison-holding-delete-dialog.component';
import { ComparisonHoldingService } from 'app/entities/comparison-holding/comparison-holding.service';

describe('Component Tests', () => {
    describe('ComparisonHolding Management Delete Component', () => {
        let comp: ComparisonHoldingDeleteDialogComponent;
        let fixture: ComponentFixture<ComparisonHoldingDeleteDialogComponent>;
        let service: ComparisonHoldingService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [H2HTestModule],
                declarations: [ComparisonHoldingDeleteDialogComponent]
            })
                .overrideTemplate(ComparisonHoldingDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(ComparisonHoldingDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ComparisonHoldingService);
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
