/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { H2HTestModule } from '../../../test.module';
import { ComparisonHoldingUpdateComponent } from 'app/entities/comparison-holding/comparison-holding-update.component';
import { ComparisonHoldingService } from 'app/entities/comparison-holding/comparison-holding.service';
import { ComparisonHolding } from 'app/shared/model/comparison-holding.model';

describe('Component Tests', () => {
    describe('ComparisonHolding Management Update Component', () => {
        let comp: ComparisonHoldingUpdateComponent;
        let fixture: ComponentFixture<ComparisonHoldingUpdateComponent>;
        let service: ComparisonHoldingService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [H2HTestModule],
                declarations: [ComparisonHoldingUpdateComponent]
            })
                .overrideTemplate(ComparisonHoldingUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(ComparisonHoldingUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ComparisonHoldingService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new ComparisonHolding(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.comparisonHolding = entity;
                    // WHEN
                    comp.save();
                    tick(); // simulate async

                    // THEN
                    expect(service.update).toHaveBeenCalledWith(entity);
                    expect(comp.isSaving).toEqual(false);
                })
            );

            it(
                'Should call create service on save for new entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new ComparisonHolding();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.comparisonHolding = entity;
                    // WHEN
                    comp.save();
                    tick(); // simulate async

                    // THEN
                    expect(service.create).toHaveBeenCalledWith(entity);
                    expect(comp.isSaving).toEqual(false);
                })
            );
        });
    });
});
