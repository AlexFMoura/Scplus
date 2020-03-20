package com.senai.scplus.thymeleaf;

import java.util.HashSet;
import java.util.Set;

import org.thymeleaf.dialect.AbstractProcessorDialect;
import org.thymeleaf.processor.IProcessor;
import org.thymeleaf.standard.StandardDialect;

import com.senai.scplus.thymeleaf.processor.ClassForErrorAttributeTagProcessor;
import com.senai.scplus.thymeleaf.processor.MenuAttributeTagProcessor;
import com.senai.scplus.thymeleaf.processor.MessageElementTagProcessor;
import com.senai.scplus.thymeleaf.processor.OrderElementTagProcessor;
import com.senai.scplus.thymeleaf.processor.PaginationElementTagProcessor;

public class SenaiDialect extends AbstractProcessorDialect {

	public SenaiDialect() {
		super("Senai Scplus", "scplus", StandardDialect.PROCESSOR_PRECEDENCE);
	}
	
	@Override
	public Set<IProcessor> getProcessors(String dialectPrefix) {
		final Set<IProcessor> processadores = new HashSet<>();
		processadores.add(new ClassForErrorAttributeTagProcessor(dialectPrefix));
		processadores.add(new MessageElementTagProcessor(dialectPrefix));
		processadores.add(new OrderElementTagProcessor(dialectPrefix));
		processadores.add(new PaginationElementTagProcessor(dialectPrefix));
		processadores.add(new MenuAttributeTagProcessor(dialectPrefix));
		return processadores;
	}

}
