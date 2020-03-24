package com.senai.ecplus.thymeleaf;

import java.util.HashSet;
import java.util.Set;

import org.thymeleaf.dialect.AbstractProcessorDialect;
import org.thymeleaf.processor.IProcessor;
import org.thymeleaf.standard.StandardDialect;

import com.senai.ecplus.thymeleaf.processor.ClassForErrorAttributeTagProcessor;
import com.senai.ecplus.thymeleaf.processor.MenuAttributeTagProcessor;
import com.senai.ecplus.thymeleaf.processor.MessageElementTagProcessor;
import com.senai.ecplus.thymeleaf.processor.OrderElementTagProcessor;
import com.senai.ecplus.thymeleaf.processor.PaginationElementTagProcessor;

public class SenaiDialect extends AbstractProcessorDialect {

	public SenaiDialect() {
		super("Senai Ecplus", "ecplus", StandardDialect.PROCESSOR_PRECEDENCE);
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
