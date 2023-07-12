package com.example.dkmh.learn;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Date;
import org.hibernate.annotations.ValueGenerationType;
import org.hibernate.tuple.AnnotationValueGeneration;
import org.hibernate.tuple.GenerationTiming;
import org.hibernate.tuple.ValueGenerator;

@ValueGenerationType(generatedBy = FunctionCreationValueGeneration.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface FunctionCreationTimestamp {
}

class FunctionCreationValueGeneration implements AnnotationValueGeneration<FunctionCreationTimestamp> {

	@Override
	public GenerationTiming getGenerationTiming() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ValueGenerator<?> getValueGenerator() {
		// TODO Auto-generated method stub
		return (sesstion, owner) -> new Date();
	}

	@Override
	public boolean referenceColumnInSql() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getDatabaseGeneratedReferencedColumnValue() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void initialize(FunctionCreationTimestamp annotation, Class<?> propertyType) {

	}

}
